(ns lepiane.home
  (:require [lepiane.components.block :as block]
            [lepiane.data.houses :as houses]
            [lepiane.houses.main :as house]
            [lepiane.data.services :as services]

            [lepiane.data.lang :as lang]))

(defn nav-house
  [house-data]
  (fn [ev]
    (house/set-house! house-data)))

(defn houses [houses-data services-data]
  [:div {:style {:margin-top "-50px"
                 :margin-left "14%"}}
   [:h3 {:style {:margin-bottom "10px"
                 :margin-top "20px"}}
    (lang/string [:sections :houses :title]) ":"]
   [:div {:style {:display "inline-block"}}
    (let [house-data (first houses-data)]
      [block/block house-data (nav-house house-data)])]
   (for [house-data (rest houses-data)]
     [:div {:key (str (gensym) "-" (:label house-data))
            :style {:display "inline-block"
                    :width "33%"
                    :float "right"}}
      [block/block house-data (nav-house house-data)]])
   [:h3 {:style {:margin-bottom "10px"
                 :margin-top "20px"}}
    (lang/string [:sections :services :title]) ":"]
   [:div {:style {:display "inline-block"}}
    [block/block (first services-data)]]
   (for [service-data (rest services-data)]
     [:div {:key (str (gensym) "-" (:label service-data))
            :style {:display "inline-block"
                    :width "33%"
                    :float "right"}}
      [block/block service-data]])])

(defn home-page []
  [:div
   [houses houses/house-data services/service-data]])
