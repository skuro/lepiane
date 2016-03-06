(ns lepiane.contacts)

(defn email-link [{:keys [to subject body]}]
  (str "mailto:" to "?subject=" subject "&body=" body))

(defn id->elem [id]
  (.getElementById js/document id))

(defn send-form []
  (let [subj (id->elem "contact-subject")
        body (id->elem "contact-body")
        link (email-link {:to "calaspiane@gmail.com"
                          :subject subj
                          :body body})]
    (set! (.-href (.-location js/window)) link)))

(defn contacts-page []
  [:div {:class "row"
         :style {:font-family "Oswald, serif"
                 :margin-left "20%"}}
   [:h3 "Contattaci"]
   [:form
    [:div {:class "form-group"}
     [:label "Oggetto"]
     [:input {:id "contact-subject"
              :type "text"
              :class "form-control"
              :placeholder "ad esempio: Richiesta informazioni"
              :style {:width "500px"}}]]
    [:div {:class "form-group"}
     [:label "Testo"]
     [:textarea {:id "contact-body"
                 :class "form-control"
                 :placeholder "ad esempio: Vorrei maggiori informazioni sul Monolocale per il periodo di..."
                 :style {:width "500px"}}]]
    [:button {:type "button"
              :class "btn btn-primary"
              :on-click send-form} "Invia"]]])
