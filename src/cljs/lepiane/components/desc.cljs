(ns lepiane.components.desc)

(defn description [desc]
  [:div.rounded {:style {:background-color "#b2d0ff"
                         :border "2px solid #ff0"}}
   [:div.container-fluid
    [:div.row {:style {:margin-top "5px"
                       :margin-bottom "-5px"}}
     [:div.col-xs-1]
     [:div.col-xs-11 {:style {:margin-left "-30px"}}
      [:p desc]]]]])
