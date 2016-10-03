(ns lepiane.data.houses
  (:require [lepiane.data.lang :as lang]))

(def house-data
  [{:id :main
    :pic "/img/padronale.png"
    :label [:sections :houses :main :label]
    :link "/houses/main"
    :desc [:span
           [:h4 "Caratteristiche principali"]
           [:ul
            [:li "3 camere"]
            [:li "2 bagni"]
            [:li "ampio salone"]
            [:li "camino"]
            [:li "6 posti letto"]]]}
   {:id :monolocale
    :pic "/img/monolocale.png"
    :label [:sections :houses :single :label]
    :link "/houses/monolocale"}
   {:id :bilocale
    :pic "/img/bilocale.png"
    :label [:sections :houses :double :label]
    :link "/houses/bilocale"}])

(defn get-house
  [id]
  (first (filter #(= id (:id %)) house-data)))
