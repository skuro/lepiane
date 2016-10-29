(ns lepiane.services
  (:require [lepiane.components.block :as b]

            [lepiane.state :as state]
            [lepiane.data.lang :as lang]))

(defn set-service!
  [state service]
  (.log js/console "service is" service)
  (swap! state assoc ::service service))

(defn get-service
  [state]
  (::service @state))

(defn spacer []
  [:div.col-xs-1])

(defn description [service]
  [:div.container-fluid
   [:div.row
    [:div.col-xs-11 {:style {:margin-left "-30px"}}
     [:h4 (lang/string [:sections :services (:id service) :label])]
     [:p service]]]])

(defn service-page []
  (let [service (get-service state/state)]
    [:div.container
     [:div.row
      [spacer]
      [:div.col-xs-2
       [:a {:href "/"
            :on-click #()}
        (lang/string [:links :back])]]
      [:div.col-xs-10 " "]]
     [:div.row
      [spacer]
      [:div.col-xs-2
       [b/block service]]
      [:div.col-xs-6
       [description service]]]]))
