(ns lepiane.data.services)

(def service-data
  [{:id :pool
    :pic "/img/piscina.png"
    :label [:sections :services :pool :label]
    :link "/services/pool"}
   {:id :rentals
    :pic "/img/padronale.png"
    :label [:sections :services :rentals :label]
    :link "/services/rentals"}
   {:id :transport
    :pic "/img/padronale.png"
    :label [:sections :services :transport :label]
    :link "/services/transport"}])

(defn get-service
  "Finds a service by id"
  [id]
  (first (filter #(= id (:id %)) service-data)))
