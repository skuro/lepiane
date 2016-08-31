(ns lepiane.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [lepiane.middleware :refer [wrap-middleware]]
            [environ.core :refer [env]]
            [clojure.string :as s]))

(def ie-support
  "<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js'></script>
      <script src='https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js'></script>
    <![endif]-->")

(def mount-target
  [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(def default-language "en")

(defn parse-language
  [lang]
  (if lang
    (-> lang
        (s/split #";")
        (get 0)
        (s/split #",")
        (get 0 default-language)
        (->> (take 2)
             (apply str)))
    default-language))

(defn lang-detect
  [req]
  (let [lang-header (get-in req [:headers "accept-language"])
        lang (parse-language lang-header)]
    [:script {:id "__lang-detect"
              :type "lepiane/language-detection"} lang]))

(defn loading-page
  [req]
  (html5
   [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
    (include-css "/css/bootstrap.min.css")
    (include-css "/css/font-awesome.min.css")
    (include-css "//cdnjs.cloudflare.com/ajax/libs/normalize/3.0.1/normalize.min.css")
    (include-css "//fonts.googleapis.com/css?family=Oswald:400,300,700")
    (include-css "//fonts.googleapis.com/css?family=Dancing+Script:700")
    (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))
    ie-support]
    [:body
     mount-target
     (include-js "//code.jquery.com/jquery-1.10.2.min.js")
     (include-js "/js/bootstrap.min.js")
     (include-js "/js/jquery.backstretch.min.js")
     (include-js "/js/app.js")
     (lang-detect req)]))

(def cards-page
  (html5
   [:head
     [:meta {:charset "utf-8"}]]
    [:body
     mount-target
     (include-js "/js/app_devcards.js")]))

(defroutes routes
  (GET "/" [] loading-page)
  (GET "/map" [] loading-page)
  (GET "/contacts" [] loading-page)
  (GET "/houses/*" [] loading-page)
  (resources "/")
  (not-found "Not Found"))

(def app (wrap-middleware #'routes))
