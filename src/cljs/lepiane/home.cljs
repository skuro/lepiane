(ns lepiane.home)

(def house-data
  [{:pic "img/monolocale.png"
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
    [:img {:class "rounded"
            :src pic
            :style {:width "120px"
                    :height "100px"
                    :margin-bottom "5px"
                    :float "left"}}]
    [:p {:style {:font-size "1.2em"}}
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
   [:div {:class "container logo"
          :style {:text-align "center"
                  :margin-top "25px"}}
    [:img {:src "/img/logo.png"
           :style {:text-align "center"
                   :width "100"}}]]
   [:div {:class "row"
          :style {:text-align "center"
                  :margin-top "-25px"}}
    [:div {:class "col-lg-8 col-lg-offset-2"}
     [:h1 {:style {:font-size "70px"
                   :font-family "Oswald, sans-serif"
                   :font-weight 300}}
      "Case vacanze alle Piane"]
     [:h3 {:style {:font-size "30px"
                   :font-family "Oswald, sans-serif"
                   :font-weight 100
                   :margin-top "-35px"
                   :margin-bottom "25px"}}
      "Mare e relax a Calasetta"]]]
   [:div {:class "row"}
    [:div {:class "col-lg-8 col-lg-offset-2 row-fluid"}
     (houses house-data)]]])
