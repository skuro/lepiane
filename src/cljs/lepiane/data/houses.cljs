(ns lepiane.data.houses
  (:require [lepiane.data.lang :as lang]))

(def house-data
  [{:id :main
    :pic "/img/padronale.png"
    :label [:sections :houses :main :label]
    :link "/houses/main"
    :desc [:span
           [:ul
            [:li "3 camere"]
            [:li "2 bagni"]
            [:li "ampio salone"]
            [:li "camino"]
            [:li "6 posti letto"]]]}
   {:id :monolocale
    :pic "/img/monolocale.png"
    :label [:sections :houses :single :label]
    :link "/houses/monolocale"
    :desc [:span
           [:ul
            [:li "1 camera"]
            [:li "1 bagno"]
            [:li "cucina abitabile"]
            [:li "2 ampie verande"]
            [:li "2 posti letto (+ 1 bambino)"]]]}
   {:id :bilocale
    :pic "/img/bilocale.png"
    :label [:sections :houses :double :label]
    :link "/houses/bilocale"
    :desc [:span
           [:ul
            [:li "2 camere"]
            [:li "1 bagno"]
            [:li "cucina abitabile"]
            [:li "2 ampie verande con vista mare"]
            [:li "4 posti letto (+ 1 bambino)"]]]}])

(defn get-house
  [id]
  (first (filter #(= id (:id %)) house-data)))
