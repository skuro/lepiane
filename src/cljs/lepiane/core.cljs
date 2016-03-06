(ns lepiane.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]

              ;pages
              [lepiane.home :as home]))

;; -------------------------
;; Views

(defn about-page []
  [:div [:h2 "About lepiane"]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn current-page []
  [:div [(session/get :current-page)]
   [:div {:class "background"}
    [:img {:src "/img/sardegna.png"
           :class "background"}]]])

;; -------------------------
;; Routes

(secretary/defroute "/" []
  (session/put! :current-page #'home/home-page))

(secretary/defroute "/about" []
  (session/put! :current-page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
