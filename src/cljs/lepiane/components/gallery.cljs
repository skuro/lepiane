(ns lepiane.components.gallery)

(defn gallery
  [pics]
  [:div.col-xs-6 {:style {:height "100px"
                          :padding-left "0px"
                          :padding-right "0px"
                          :overflow "scroll"}}
   [:div {:style {:width "200%"
                  :position "absolute"
                  :z-index "-1"
                  :overflow "scroll"
                  :padding-left "30px"}}
    (for [{:keys [src alt]} pics]
      [:img {:key (gensym)
             :style {:height "100px"
                     :width "139px"
                     :border-radius "5px"
                     :margin-right "10px"}
             :src src
             :alt alt}])]])
