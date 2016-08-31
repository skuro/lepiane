(ns lepiane.home
  (:require [lepiane.components.block :as block]
            [lepiane.data.houses :as houses]
            [lepiane.data.services :as services]))

(defn houses [houses-data services-data]
  [:div {:style {:margin-top "-50px"
                 :margin-left "14%"}}
   [:h3 {:style {:margin-bottom "10px"
                 :margin-top "20px"}}
    "Le nostre case:"]
   [:div {:style {:display "inline-block"}}
    [block/block (first houses-data)]]
   (for [house-data (rest houses-data)]
     [:div {:key (str (gensym) "-" (:label house-data))
            :style {:display "inline-block"
                    :width "33%"
                    :float "right"}}
      [block/block house-data]])
   [:h3 {:style {:margin-bottom "10px"
                 :margin-top "20px"}}
    "I nostri servizi:"]
   [:div {:style {:display "inline-block"}}
    (block/block (first services-data))]
   (for [service-data (rest services-data)]
     [:div {:key (str (gensym) "-" (:label service-data))
            :style {:display "inline-block"
                    :width "33%"
                    :float "right"}}
      (block/block service-data)])])

(defn home-page []
  [:div
   [houses houses/house-data services/service-data]])
