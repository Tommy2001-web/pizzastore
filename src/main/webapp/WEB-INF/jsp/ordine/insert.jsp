<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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

            <!-- BANNER SILVER/GOLD -->
            <div id="promoBanner" class="alert alert-success mt-3" style="display:none;"></div>

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
                        <!-- ERRORE lato client -->
                        <div id="codiceError" class="error_field"></div>

                        <!-- ERRORE lato server -->
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
                                        <option value="${c.id}" data-ordini="${c.numeroOrdini}"
                                            ${c.id == insert_ordine_attr.clienteId ? 'selected' : ''}>
                                                ${c.nome} ${c.cognome}
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

                        <c:forEach items="${pizza_list}" var="p">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input pizzaCheck"
                                       type="checkbox"
                                       name="pizzaIds"
                                       value="${p.id}"
                                       data-prezzo="${p.prezzoBase}"
                                       id="pizza${p.id}"/>

                                <label class="form-check-label" for="pizza${p.id}">
                                        ${p.descrizione} (&euro;${p.prezzoBase})
                                </label>
                            </div>
                        </c:forEach>

                        <form:errors path="pizzaIds" cssClass="error_field"/>
                    </div>

                    <!-- PREZZO TOTALE -->
                    <div class="mt-3">
                        <p>Totale: <span id="totale">0.00</span> &euro;</p>
                        <p id="scontoRiga" style="display:none;">
                            Sconto: <span id="sconto">0.00</span> &euro;
                        </p>
                        <h4>Finale: <span id="finale">0.00</span> &euro;</h4>
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
    const checkboxes = document.querySelectorAll(".pizzaCheck");
    const clienteSelect = document.getElementById("clienteId");
    const promoBanner = document.getElementById("promoBanner");
    const totaleEl = document.getElementById("totale");
    const scontoEl = document.getElementById("sconto");
    const scontoRiga = document.getElementById("scontoRiga");
    const finaleEl = document.getElementById("finale");

    function aggiornaTotale() {
        let totale = 0;
        checkboxes.forEach(cb => {
            if(cb.checked) totale += parseFloat(cb.dataset.prezzo || 0);
        });

        let numOrdini = 0;
        let selectedCliente = clienteSelect.options[clienteSelect.selectedIndex];
        if(selectedCliente) numOrdini = parseInt(selectedCliente.getAttribute("data-ordini") || 0);

        let sconto = 0;
        promoBanner.style.display = "none";

        if(numOrdini === 9) {
            sconto = totale * 0.10;
            promoBanner.style.display = "block";
            promoBanner.innerHTML = "Questo è il 10° ordine → sei un Cliente SILVER (10% sconto)";
        } else if(numOrdini === 19) {
            sconto = totale * 0.20;
            promoBanner.style.display = "block";
            promoBanner.innerHTML = "Questo è il 20° ordine → Cliente GOLD (20% sconto)";
        }

        totaleEl.innerText = totale.toFixed(2);
        if(sconto > 0) {
            scontoRiga.style.display = "block";
            scontoEl.innerText = "-" + sconto.toFixed(2);
        } else {
            scontoRiga.style.display = "none";
        }

        finaleEl.innerText = (totale - sconto).toFixed(2);
    }

    checkboxes.forEach(cb => cb.addEventListener("change", aggiornaTotale));
    clienteSelect.addEventListener("change", aggiornaTotale);

    // inizializza
    aggiornaTotale();

    // validazione lato client
    const codiceError = document.getElementById("codiceError");
    if(codiceField.value.trim() === "") {
        codiceError.innerText = "Il codice è obbligatorio";
        codiceField.classList.add("is-invalid");
        valid = false;
    } form.addEventListener("submit", function(event){
        let valid = true;

        document.getElementById("codiceError").innerText = "";
        document.getElementById("clienteError").style.display = "none";
        document.getElementById("pizzaError").style.display = "none";

        const codiceField = document.getElementById("codice");
        if(codiceField.value.trim() === "") {
            document.getElementById("codiceError").innerText = "Il codice è obbligatorio";
            codiceField.classList.add("is-invalid");
            valid = false;
        }

        if(clienteSelect.value === "") {
            clienteSelect.classList.add("is-invalid");
            document.getElementById("clienteError").style.display = "block";
            valid = false;
        }

        if(!Array.from(checkboxes).some(cb => cb.checked)) {
            document.getElementById("pizzaError").style.display = "block";
            valid = false;
        }

        if(!valid) event.preventDefault();
    });
</script>
</body>
</html>