<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp" />

    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
    <style>
        .ui-autocomplete-loading {
            background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
        }
        .error_field {
            color: red;
        }
    </style>
    <title>Inserisci nuovo</title>

</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp" />

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <%-- se l'attributo in request ha errori --%>
        <spring:hasBindErrors  name="pizza_edit_attr">
            <%-- alert errori --%>
            <div class="alert alert-danger " role="alert">
                Attenzione!! Sono presenti errori di validazione
            </div>
        </spring:hasBindErrors>

        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>

        <div class='card'>
            <div class='card-header'>
                <h5>Inserisci nuovo elemento</h5>
            </div>
            <div class='card-body'>

                <h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>


                <form:form method="post" modelAttribute="edit_pizza_attr"
                           action="${pageContext.request.contextPath}/pizza/update"
                           novalidate="novalidate" class="row g-3">
                    <form:hidden path="id"/>
                    <div class="col-md-6">
                        <label for="descrizione" class="form-label">Descrizione <span class="text-danger">*</span></label>
                        <spring:bind path="descrizione">
                            <input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${edit_pizza_attr.descrizione }">
                        </spring:bind>
                        <div class="error_field" id="descrizioneError"></div>
                        <form:errors  path="descrizione" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="ingredienti" class="form-label">Ingredienti <span class="text-danger">*</span></label>
                        <spring:bind path="ingredienti">
                            <input type="text" name="ingredienti" id="ingredienti" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire gli ingredienti" value="${edit_pizza_attr.ingredienti }">
                        </spring:bind>
                        <div class="error_field" id="ingredientiError"></div>
                        <form:errors  path="ingredienti" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="prezzoBase" class="form-label">Prezzo Base <span class="text-danger">*</span></label>
                        <spring:bind path="prezzoBase">
                            <input type="number" name="prezzoBase" id="prezzoBase" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il prezzo" value="${edit_pizza_attr.prezzoBase }">
                        </spring:bind>
                        <div class="error_field" id="prezzoBaseError"></div>
                        <form:errors  path="prezzoBase" cssClass="error_field" />
                    </div>
                    <div class="col-md-6">
                        <label for="attivo" class="form-label">Attività <span class="text-danger">*</span></label>
                        <spring:bind path="attivo">
                            <select class="form-select ${status.error ? 'is-invalid' : ''}" id="attivo" name="attivo"
                                    required>
                                <option value="true" ${edit_pizza_attr.attivo == 'true'?'selected':''} >Attivo
                                </option>
                                <option value="false" ${edit_pizza_attr.attivo == 'false'?'selected':''} >Disattivo
                                </option>
                            </select>
                        </spring:bind>
                        <form:errors path="attivo" cssClass="error_field"/>
                    </div>

                    <div class="col-md-12 d-flex align-items-end justify-content-end">
                        <button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-warning">Conferma</button>
                    </div>
                </form:form>

                <!-- end card-body -->
            </div>
            <!-- end card -->
        </div>
        <!-- end container -->
    </div>
    <!-- end main -->
</main>
<jsp:include page="../footer.jsp" />
<script>
    document.querySelector("form").addEventListener("submit", function (event) {

        let valid = true;

        // campi
        const descrizione = document.getElementById("descrizione");
        const ingredienti = document.getElementById("ingredienti");
        const prezzoBase = document.getElementById("prezzoBase");

        // reset errori
        resetField(descrizione, "descrizioneError");
        resetField(ingredienti, "ingredientiError");
        resetField(prezzoBase, "prezzoBaseError");

        // VALIDAZIONE NOME
        if (descrizione.value.trim() === "") {
            setError(descrizione, "descrizioneError", "Il descrizione è obbligatorio");
            valid = false;
        }

        // VALIDAZIONE COGNOME
        if (ingredienti.value.trim() === "") {
            setError(ingredienti, "ingredientiError", "Il ingredienti è obbligatorio");
            valid = false;
        }

        // VALIDAZIONE INDIRIZZO
        if (prezzoBase.value.trim() === "") {
            setError(prezzoBase, "prezzoBaseError", "L'prezzoBase è obbligatorio");
            valid = false;
        }

        if (!valid) {
            event.preventDefault(); // blocca submit
        }
    });

    function setError(input, errorId, message) {
        input.classList.add("is-invalid");
        document.getElementById(errorId).innerText = message;
    }

    function resetField(input, errorId) {
        input.classList.remove("is-invalid");
        document.getElementById(errorId).innerText = "";
    }

    const descrizione = document.getElementById("descrizione");
    const ingredienti = document.getElementById("ingredienti");
    const prezzoBase = document.getElementById("prezzoBase");

    // quando l'utente scrive → reset errore
    descrizione.addEventListener("change", () => resetField(descrizione, "descrizioneError"));
    ingredienti.addEventListener("change", () => resetField(ingredienti, "ingredientiError"));
    prezzoBase.addEventListener("change", () => resetField(prezzoBase, "prezzoBaseError"));
</script>
</body>
</html>