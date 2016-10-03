(ns lepiane.houses.main
  (:require [reagent.core :as reagent :refer [atom]]

            [lepiane.components.block :as b]
            [lepiane.components.desc :as d]
            [lepiane.components.gallery :as g]
            [lepiane.data.houses :as h]

            [lepiane.data.lang :as lang]))

(defonce house (atom nil))

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
     [d/description (:desc @house)]]]
   [:div.row {:style {:margin-top "5em"}}
    [spacer]
    (let [skip (atom 0)]
      [g/gallery [{:src (:pic @house)}
                  {:src "/img/monolocale.png"}]
       skip])]])
