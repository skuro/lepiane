(ns lepiane.state
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce state (atom {}))
