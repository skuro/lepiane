(ns lepiane.data.houses
  (:require [lepiane.data.lang :as lang]))

(def house-data
  [{:id :main
    :pic "/img/padronale.png"
    :label [:sections :houses :main :label]
    :link "/houses/main"
    :desc {:size 110
           :toilets 2
           :pax {:adults 6
                 :children 2}}}
   {:id :monolocale
    :pic "/img/monolocale.png"
    :label [:sections :houses :single :label]
    :link "/houses/monolocale"
    :desc {:size 45
           :toilets 1
           :pax {:adults 2
                 :children 1}}}
   {:id :bilocale
    :pic "/img/bilocale.png"
    :label [:sections :houses :double :label]
    :link "/houses/bilocale"
    :desc {:size 65
           :toilets 1
           :pax {:adults 4}}}])

(defn get-house
  [id]
  (first (filter #(= id (:id %)) house-data)))
