(ns lepiane.components.gallery)

(defonce arrows {:left {:char "<"
                        :float "left"}
                 :right {:char ">"
                         :float "right"}})

(defn get-skip
  [state]
  (get @state ::skip 0))

(defn set-skip!
  [state skip]
  (swap! state assoc ::skip skip))

(defn next!
  [state]
  (set-skip! state (inc (get-skip state))))

(defn arrow
  [direction state]
  [:a {:href "#"
       :on-click #(next! state)}
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
  [pics state]
  (let [skip (get-skip state)
        _ (println "skipping count: " skip)
        visible (take 10 (drop skip (cycle pics)))]
    [:div.col-xs-6 {:style {:height "100px"
                            :padding-left "0px"
                            :padding-right "0px"
                            :overflow "hidden"}}
     [arrow :left state]
     [arrow :right state]
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
