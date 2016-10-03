(ns lepiane.components.gallery
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce arrows {:left {:char "<"
                        :float "left"}
                 :right {:char ">"
                         :float "right"}})

(defn arrow
  [direction step]
  [:a {:href "#"
       :on-click #(swap! step inc)}
   [:span {:style {:display "block"
                   :margin "auto 0px auto 0px"
                   :padding "0px"
                   :width "30px"
                   :background-color "lightgrey"
                   :color "white"
                   :text-align (get-in arrows [direction :float])
                   :font-size "2em"
                   :float (get-in arrows [direction :float])
                   :height "100%"}} (get-in arrows [direction :char])]])

(defn gallery
  [pics skip]
  (let [visible (take 10 (drop @skip (cycle pics)))]
    (println "rerendering:" @skip)
    [:div.col-xs-6 {:style {:height "100px"
                            :padding-right "0px"
                            :overflow "hidden"}}
     [arrow :left skip]
     [arrow :right skip]
     [:div {:style {:width "200%"
                    :position "absolute"
                    :z-index "-1"
                    :overflow-x "scroll"
                    :padding-left "30px"}}
      (for [{:keys [src alt]} visible]
        [:img {:key (gensym)
               :style {:height "100px"
                       :width "139px"}
               :src src
               :alt alt}])]]))
