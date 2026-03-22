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
        <spring:hasBindErrors  name="insert_cliente_attr">
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


                <form:form method="post" modelAttribute="insert_cliente_attr" action="save" novalidate="novalidate" class="row g-3">

                    <div class="col-md-6">
                        <label for="nome" class="form-label">Nome <span class="text-danger">*</span></label>
                        <spring:bind path="nome">
                            <input type="text" name="nome" id="nome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il nome" value="${insert_cliente_attr.nome }">
                        </spring:bind>
                        <div class="error_field" id="nomeError"></div>
                        <form:errors  path="nome" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="cognome" class="form-label">Cognome <span class="text-danger">*</span></label>
                        <spring:bind path="cognome">
                            <input type="text" name="cognome" id="cognome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il cognome" value="${insert_cliente_attr.cognome }">
                        </spring:bind>
                        <div class="error_field" id="cognomeError"></div>
                        <form:errors  path="cognome" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="indirizzo" class="form-label">indirizzo <span class="text-danger">*</span></label>
                        <spring:bind path="indirizzo">
                            <input type="text" name="indirizzo" id="indirizzo" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire l'indirizzo" value="${insert_cliente_attr.indirizzo }">
                        </spring:bind>
                        <div class="error_field" id="indirizzoError"></div>
                        <form:errors  path="indirizzo" cssClass="error_field" />
                    </div>


<%--                    <div class="col-md-3">--%>
<%--                        <label for="attivo" class="form-label">Sesso <span class="text-danger">*</span></label>--%>
<%--                        <spring:bind path="attivo">--%>
<%--                            <select class="form-select ${status.error ? 'is-invalid' : ''}" id="attivo" name="attivo" required>--%>
<%--                                <option value="" selected> - Selezionare - </option>--%>
<%--                                <option value="Attivo" ${insert_cliente_attr.attivo == 'Attivo'?'selected':''} >Attivo</option>--%>
<%--                                <option value="Disattivo" ${insert_cliente_attr.cliente == 'Disattivo'?'selected':''} >Disattivo</option>--%>
<%--                            </select>--%>
<%--                        </spring:bind>--%>
<%--                        <form:errors  path="attivo" cssClass="error_field" />--%>
<%--                    </div>--%>
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
    document.querySelector("form").addEventListener("submit", function(event) {

        let valid = true;

        // campi
        const nome = document.getElementById("nome");
        const cognome = document.getElementById("cognome");
        const indirizzo = document.getElementById("indirizzo");

        // reset errori
        resetField(nome, "nomeError");
        resetField(cognome, "cognomeError");
        resetField(indirizzo, "indirizzoError");

        // VALIDAZIONE NOME
        if (nome.value.trim() === "") {
            setError(nome, "nomeError", "Il nome è obbligatorio");
            valid = false;
        }

        // VALIDAZIONE COGNOME
        if (cognome.value.trim() === "") {
            setError(cognome, "cognomeError", "Il cognome è obbligatorio");
            valid = false;
        }

        // VALIDAZIONE INDIRIZZO
        if (indirizzo.value.trim() === "") {
            setError(indirizzo, "indirizzoError", "L'indirizzo è obbligatorio");
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

    const nome = document.getElementById("nome");
    const cognome = document.getElementById("cognome");
    const indirizzo = document.getElementById("indirizzo");

    // quando l'utente scrive → reset errore
    nome.addEventListener("change", () => resetField(nome, "nomeError"));
    cognome.addEventListener("change", () => resetField(cognome, "cognomeError"));
    indirizzo.addEventListener("change", () => resetField(indirizzo, "indirizzoError"));
</script>
</body>
</html>