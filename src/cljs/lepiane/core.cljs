(ns lepiane.core
    (:require [reagent.core :as reagent]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]

              ; views
              [lepiane.home :as home]
              [lepiane.contacts :as contacts]
              [lepiane.map :as map]
              [lepiane.data.houses :as houses]
              [lepiane.houses.main :as hmain]

              ; utils
              [lepiane.data.lang :as lang]
              [lepiane.state :as state]))

;; -------------------------
;; Main page

(defn navbar []
  [:div {:style {:text-align "center"
                 :margin-top "20px"
                 :margin-bottom "-60px"
                 :z-index 100}
         :class "navbar"}
   [lang/lang-switcher (name (or (:lang (lang/get-language state/state))
                                 :en))]
   [:a {:href "/"} (lang/string [:navbar :home])]
   "|"
   [:a {:href "/map"} (lang/string [:navbar :where])]
   "|"
   [:a {:href "/contacts"} (lang/string [:navbar :contacts])]])

(defn title []
  [:div
   [:div {:class "container logo"
          :style {:text-align "center"
                  :margin-top "25px"}}]
   [:div {:class "row"
          :style {:text-align "center"
                  :margin-top "-25px"}}
    [:div {:class "col-lg-8 col-lg-offset-2"}
     [:h1 {:style {:font-family "Oswald, sans-serif"
                   :font-weight 300}}
      [:img {:src "/img/logo.png"
             :class "logo"
             :style {:text-align "center"
                     :margin-right "50px"}}]
      (lang/string [:title :main])]
     [:h3 {:style {:font-family "Oswald, sans-serif"
                   :font-weight 100
                   :margin-bottom "70px"}}
      (lang/string [:title :tagline])]]]])

(defn current-page []
  [:div
   (navbar)
   (title)
   [:div {:class "row"}
    [:div {:class "col-lg-8 col-lg-offset-2 row-fluid"}
     [(session/get :current-page)]]]
   [:div {:class "background"}
    [:img {:src "/img/sardegna.png"
           :class "background"}]]])

;; -------------------------
;; Routes

(secretary/defroute "/" []
  (session/put! :current-page #'home/home-page))

(secretary/defroute "/contacts" []
  (session/put! :current-page #'contacts/contacts-page))

(secretary/defroute "/map" []
  (session/put! :current-page #'map/map-page))

(secretary/defroute "/houses/:house" [house]
  (hmain/set-house! state/state (houses/get-house (keyword house)))
  (session/put! :current-page #'hmain/main-house-page))

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
