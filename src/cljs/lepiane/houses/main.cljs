(ns lepiane.houses.main
  (:require [reagent.core :as reagent :refer [atom]]

            [lepiane.components.block :as b]
            [lepiane.components.desc :as d]
            [lepiane.data.houses :as h]

            [lepiane.data.lang :as lang]))

(def house (atom nil))

(defn set-house!
  [new-house]
  (reset! house new-house))

(defn spacer []
  [:div.col-xs-1])

(defn nav-clear-house
  []
  (fn [ev]
    (set-house! nil)))

(defn main-house-page []
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
     [b/block @house]]
    [:div.col-xs-6
     [d/description (:desc @house)]]]])
