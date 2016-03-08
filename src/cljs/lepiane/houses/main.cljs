(ns lepiane.houses.main
  (:require [lepiane.components.block :as b]
            [lepiane.components.desc :as d]
            [lepiane.data.houses :as h]))

(def house (first (filter #(= "Principale" (:label %)) h/house-data)))

(defn spacer []
  [:div.col-xs-2])

(defn main-house-page []
  [:div.container
   [:div.row
    (spacer)
    [:div.col-xs-2
     [:a {:href "#"} "< indietro"]]
    [:div.col-xs-10 " "]]
   [:div.row
    (spacer)
    [:div.col-xs-2
     (b/block house)]
    [:div.col-xs-8
     (d/description (:desc house))]]])
