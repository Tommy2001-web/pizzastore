<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jqueryUI/jquery-ui.min.css" />
    <style>
        .ui-autocomplete-loading {
            background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
        }
        .error_field {
            color: red;
            font-size: 0.9em;
        }
    </style>
    <title>Inserisci Ordine</title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp" />

<main class="flex-shrink-0">
    <div class="container">

        <spring:hasBindErrors name="insert_ordine_attr">
            <div class="alert alert-danger" role="alert">
                Attenzione!! Sono presenti errori di validazione
            </div>
        </spring:hasBindErrors>

        <div class="card">
            <div class="card-header bg-warning">
                <h5>Inserisci nuovo Ordine</h5>
            </div>
            <div class="card-body">
                <h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

                <form:form method="post" modelAttribute="insert_ordine_attr"
                           action="${pageContext.request.contextPath}/ordine/save"
                           novalidate="novalidate" class="row g-3">

                    <form:hidden path="id"/>

                    <!-- CODICE -->
                    <div class="col-md-6">
                        <label for="codice" class="form-label">Codice <span class="text-danger">*</span></label>
                        <spring:bind path="codice">
                            <input type="text" id="codice" name="codice"
                                   class="form-control ${status.error ? 'is-invalid' : ''}"
                                   placeholder="Inserire il codice"
                                   value="${insert_ordine_attr.codice }">
                        </spring:bind>
                        <div class="error_field" id="codiceError"></div>
                        <form:errors path="codice" cssClass="error_field" />
                    </div>

                    <!-- CLIENTE -->
                    <div class="col-md-6">
                        <label for="clienteId" class="form-label">Cliente <span class="text-danger">*</span></label>
                        <spring:bind path="clienteId">
                            <select class="form-select ${status.error ? 'is-invalid' : ''}" id="clienteId" name="clienteId">
                                <option value="">- Selezionare -</option>
                                <c:forEach items="${cliente_list}" var="c">
                                    <c:if test="${c.attivo}">
                                        <option value="${c.id}" ${c.id == insert_ordine_attr.clienteId ? 'selected' : ''}>
                                                ${c.nome}
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </spring:bind>
                        <div class="error_field" id="clienteError" style="display:none;">Seleziona un cliente!</div>
                        <form:errors path="clienteId" cssClass="error_field" />
                    </div>

                    <!-- PIZZE -->
                    <div class="col-md-12">
                        <label class="form-label">Pizze <span class="text-danger">*</span></label>
                        <div id="pizzaCheckboxes">
                            <c:forEach items="${pizza_list}" var="p">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input pizzaCheck" type="checkbox"
                                           id="pizza${p.id}" name="pizzaIds"
                                           value="${p.id}"
                                           data-prezzo="${p.prezzoBase}"
                                        ${!p.attivo ? 'disabled' : ''}>

                                    <label class="form-check-label" for="pizza${p.id}">
                                            ${p.descrizione} (€${p.prezzoBase})
                                        <c:if test="${!p.attivo}">
                                            <span class="text-muted">(non disponibile)</span>
                                        </c:if>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="error_field" id="pizzaError" style="display:none;">Seleziona almeno una pizza!</div>
                        <form:errors path="pizzaIds" cssClass="error_field" />
                    </div>

                    <!-- PREZZO TOTALE -->
                    <div class="col-md-6">
                        <label for="costoTotale" class="form-label">Prezzo Totale</label>
                        <spring:bind path="costoTotale">
                            <input type="text" id="costoTotale" name="costoTotale" readonly
                                   class="form-control" value="${insert_ordine_attr.costoTotale != null ? insert_ordine_attr.costoTotale : 0}">
                        </spring:bind>
                        <form:hidden path="costoTotale"/>
                    </div>

                    <div class="col-12 d-flex justify-content-end">
                        <button type="submit" class="btn btn-outline-warning">Conferma</button>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../footer.jsp" />

<script>
    // Seleziona elementi
    const checkboxes = document.querySelectorAll(".pizzaCheck");
    const prezzoField = document.getElementById("costoTotale");
    const form = document.querySelector("form");
    const codiceField = document.getElementById("codice");
    const clienteSelect = document.getElementById("clienteId");

    // Funzione per calcolare prezzo totale
    function calcolaPrezzo() {
        let totale = 0;
        checkboxes.forEach(cb => {
            if(cb.checked) {
                totale += parseFloat(cb.dataset.prezzo || 0);
            }
        });
        prezzoField.value = totale.toFixed(2);
    }

    // Collega evento change a tutti i checkbox delle pizze
    checkboxes.forEach(cb => cb.addEventListener("change", calcolaPrezzo));

    // Inizializza prezzo totale a 0
    prezzoField.value = "0.00";

    // Validazione lato client al submit
    form.addEventListener("submit", function(event) {
        let valid = true;

        // Reset messaggi
        document.getElementById("codiceError").innerText = "";
        document.getElementById("clienteError").style.display = "none";
        document.getElementById("pizzaError").style.display = "none";
        codiceField.classList.remove("is-invalid");
        clienteSelect.classList.remove("is-invalid");
        checkboxes.forEach(cb => cb.classList.remove("is-invalid"));

        // Codice obbligatorio
        if(codiceField.value.trim() === "") {
            document.getElementById("codiceError").innerText = "Il codice è obbligatorio";
            codiceField.classList.add("is-invalid");
            valid = false;
        }

        // Cliente obbligatorio
        if(clienteSelect.value === "") {
            clienteSelect.classList.add("is-invalid");
            document.getElementById("clienteError").style.display = "block";
            valid = false;
        }

        // Almeno una pizza selezionata
        const pizzaSelezionate = Array.from(checkboxes).some(cb => cb.checked);
        if(!pizzaSelezionate) {
            document.getElementById("pizzaError").style.display = "block";
            valid = false;
        }

        if(!valid) {
            event.preventDefault(); // blocca submit se non valido
        }
    });
</script>
</body>
</html>