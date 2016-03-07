(ns lepiane.map)

                                        ;<iframe width="600" height="450" frameborder="0" style="border:0"
src="https://www.google.com/maps/embed/v1/place?q=calasetta&key=..." allowfullscreen></iframe>

(defn map-page []
  [:iframe {:src "https://www.google.com/maps/embed/v1/view?center=39.090843,8.367924&zoom=18&maptype=satellite&key=AIzaSyCht-yFqZjJpDjYAppo6w3QpaDqIdXkV5s"
            :frameborder "0px"
            :allowfullscreen "true"
            :style {:border "0px"}}])
