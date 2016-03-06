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

(def service-data
  [{:pic "img/piscina.png"
    :label "Piscina"
    :link "#"}
   {:pic "img/padronale.png"
    :label "Auto"
    :link "#"}
   {:pic "img/padronale.png"
    :label "Trasporto"
    :link "#"}])

(defn block [{:keys [pic label link]}]
  [:div {:style {:text-align "center"
                 :display "inline-block"}}
   [:a {:href link}
    [:img {:class "rounded houses"
            :src pic
            :style {:margin-bottom "5px"}}]
    [:p {:style {:font-size "1.3em"}}
     label]]])

(defn houses [houses-data services-data]
  [:div {:style {:margin-top "-50px"
                 :margin-left "14%"}}
   [:h3 {:style {:margin-bottom "10px"}}
    "Le nostre case:"]
   [:div {:style {:display "inline-block"}}
    (block (first houses-data))]
   (for [house-data (rest houses-data)]
     [:div {:key (str (gensym) "-" (:label house-data))
            :style {:display "inline-block"
                    :width "33%"
                    :float "right"}}
      (block house-data)])
   [:h3 {:style {:margin-bottom "10px"}}
    "I nostri servizi:"]
   [:div {:style {:display "inline-block"}}
    (block (first services-data))]
   (for [service-data (rest services-data)]
     [:div {:key (str (gensym) "-" (:label service-data))
            :style {:display "inline-block"
                    :width "33%"
                    :float "right"}}
      (block service-data)])])

(defn home-page []
  [:div
   (houses house-data service-data)])
