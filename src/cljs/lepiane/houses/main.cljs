(ns lepiane.houses.main
  (:require [lepiane.state :as state]

            [lepiane.components.block :as b]
            [lepiane.components.desc :as d]
            [lepiane.data.houses :as h]

            [lepiane.data.lang :as lang]))

(defn set-house!
  [state new-house]
  (swap! state assoc ::house new-house))

(defn get-house
  [state]
  (::house @state))

(defn spacer []
  [:div.col-xs-1])

(defn nav-clear-house
  []
  (fn [ev]
    (set-house! state/state nil)))

(defn main-house-page []
  (let [house (get-house state/state)]
    [:div.container
     [:div.row
      [spacer]
      [:div.col-xs-2
       [:a {:href "/"
            :on-click (nav-clear-house)}
        (lang/string [:links :back])]]
      [:div.col-xs-10 " "]]
     [:div.row
      [spacer]
      [:div.col-xs-2
       [b/block house]]
      [:div.col-xs-6
       [d/description (:desc house)]]]]))
