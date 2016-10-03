(ns lepiane.components.block
  (:require [lepiane.data.lang :as lang]))

(defn block
  ([{:keys [pic label link]} callback]
   [:div.rounded {:style {:text-align "center"
                          :display "inline-block"
                          :border "solid 2px #cbcbcb"
                          :background-color "#faf8f2"}}
    [:a {:href link
         :on-click callback}
     [:img {:class "rounded houses"
            :src pic
            :style {:margin-bottom "5px"}}]
     [:p {:style {:font-size "1.1em"
                  :font-family "'Dancing Script', cursive"}}
      (lang/string label)]]])
  ([content]
   [block content #()]))
