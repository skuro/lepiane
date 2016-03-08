(ns lepiane.components.block)

(defn block [{:keys [pic label link]}]
  [:div {:style {:text-align "center"
                 :display "inline-block"}}
   [:a {:href link}
    [:img {:class "rounded houses"
           :src pic
           :style {:margin-bottom "5px"}}]
    [:p {:style {:font-size "1.3em"
                 :font-family "'Dancing Script', cursive"}}
     label]]])
