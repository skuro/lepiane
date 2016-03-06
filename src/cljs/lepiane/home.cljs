(ns lepiane.home)

(def house-data
  [{:pic "img/padronale.png"
    :label "Casa padronale"
    :link "#"}
   {:pic "img/monolocale.png"
    :label "Monolocale"
    :link "#"}
   {:pic "img/bilocale.png"
    :label "Bilocale"
    :link "#"}])

(defn house [{:keys [pic label link]}]
  [:div {:style {:text-align "center"
                 :display "inline-block"}}
   [:a {:href link}
    [:img {:class "rounded houses"
            :src pic
            :style {:margin-bottom "5px"}}]
    [:p {:style {:font-size "1.3em"}}
     label]]])

(defn houses [houses-data]
  [:div {:style {:margin-top "25px"
                 :margin-left "14%"}}
   [:div {:style {:display "inline-block"}}
    (house (first houses-data))]
   (for [house-data (rest houses-data)]
     [:div {:key (str (gensym) "-" (:label house-data))
            :style {:display "inline-block"
                    :width "33%"
                    :float "right"}}
      (house house-data)])])

(defn home-page []
  [:div
   (houses house-data)])
