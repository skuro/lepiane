(ns lepiane.components.desc)

(defn description [desc]
  [:div.rounded {:style {:background-color "#b2d0ff"
                         :border "2px solid #f3f1ed"}}
   [:div.container-fluid
    [:div.row
     [:div.col-xs-1]
     [:div.col-xs-11 {:style {:margin-left "-30px"}}
      [:p desc]]]]])