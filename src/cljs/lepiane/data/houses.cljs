(ns lepiane.data.houses)

(def house-data
  [{:pic "/img/padronale.png"
    :label "Principale"
    :link "/houses/main"
    :desc [:span
           [:h4 "Caratteristiche principali"]
           [:ul
            [:li "3 camere"]
            [:li "2 bagni"]
            [:li "ampio salone"]
            [:li "camino"]
            [:li "6 posti letto"]]]}
   {:pic "/img/monolocale.png"
    :label "Monolocale"
    :link "/houses/monolocale"}
   {:pic "/img/bilocale.png"
    :label "Bilocale"
    :link "/houses/bilocale"}])
