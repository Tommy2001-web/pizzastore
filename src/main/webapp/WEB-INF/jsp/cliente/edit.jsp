<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp"/>

    <style>
        .error_field {
            color: red;
        }
    </style>
    <title>Modifica Elemento</title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp"/>

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <%-- se l'attributo in request ha errori --%>
        <spring:hasBindErrors name="edit_cliente_attr">
            <%-- alert errori --%>
            <div class="alert alert-danger " role="alert">
                Attenzione!! Sono presenti errori di validazione
            </div>
        </spring:hasBindErrors>

        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>

        <div class='card'>
            <div class='card-header'>
                <h5>Modifica elemento</h5>
            </div>
            <div class='card-body'>

                <h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

                <form:form modelAttribute="edit_cliente_attr" method="post"
                           action="${pageContext.request.contextPath }/cliente/update" novalidate="novalidate"
                           class="row g-3">
                    <form:hidden path="id"/>

                    <div class="col-md-3">
                        <label for="nome" class="form-label">Nome <span class="text-danger">*</span></label>
                        <spring:bind path="nome">
                            <input type="text" name="nome" id="nome"
                                   class="form-control ${status.error ? 'is-invalid' : ''}"
                                   placeholder="Inserire il nome" value="${edit_cliente_attr.nome }" required>
                        </spring:bind>
                        <div class="error_field" id="nomeError"></div>
                        <form:errors path="nome" cssClass="error_field"/>
                    </div>

                    <div class="col-md-3">
                        <label for="cognome" class="form-label">Cognome <span class="text-danger">*</span></label>
                        <spring:bind path="cognome">
                            <input type="text" name="cognome" id="cognome"
                                   class="form-control ${status.error ? 'is-invalid' : ''}"
                                   placeholder="Inserire il cognome" value="${edit_cliente_attr.cognome }" required>
                        </spring:bind>
                        <div class="error_field" id="cognomeError"></div>
                        <form:errors path="cognome" cssClass="error_field"/>
                    </div>

                    <div class="col-md-3">
                        <label for="indirizzo" class="form-label">Indirizzo <span class="text-danger">*</span></label>
                        <spring:bind path="indirizzo">
                            <input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="indirizzo"
                                   id="indirizzo" value="${edit_cliente_attr.indirizzo }" required>
                        </spring:bind>
                        <div class="error_field" id="indirizzoError"></div>
                        <form:errors path="indirizzo" cssClass="error_field"/>
                    </div>
                    <div class="col-md-3">
                        <label for="attivo" class="form-label">Attività <span class="text-danger">*</span></label>
                        <spring:bind path="attivo">
                            <select class="form-select ${status.error ? 'is-invalid' : ''}" id="attivo" name="attivo"
                                    required>
                                <option value="true" ${edit_cliente_attr.attivo == 'true'?'selected':''} >Attivo
                                </option>
                                <option value="false" ${edit_cliente_attr.attivo == 'false'?'selected':''} >Disattivo
                                </option>
                            </select>
                        </spring:bind>
                        <form:errors path="attivo" cssClass="error_field"/>
                    </div>

                    <%--                    <div class="col-md-6">--%>
                    <%--                        <label for="ordineSearchInput" class="form-label">Ordine: <span class="text-danger">*</span></label>--%>
                    <%--                        <spring:bind path="cliente">--%>
                    <%--                            <input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="ordineSearchInput"--%>
                    <%--                                   name="ordineInput" value="${edit_cliente_attr.ordine.nome}${empty edit_cliente_attr.ordine.nome?'':' '}${edit_cliente_attr.ordine.cognome}">--%>
                    <%--                        </spring:bind>--%>
                    <%--                        <input type="hidden" name="ordine.id" id="ordineId" value="${edit_cliente_attr.ordine.id}">--%>
                    <%--                        <form:errors  path="ordine" cssClass="error_field" />--%>
                    <%--                    </div>--%>

                    <div class="col-12">
                        <button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-warning">
                            Conferma
                        </button>
                    </div>

                </form:form>

                <!-- end card-body -->
            </div>
            <!-- end card -->
        </div>

        <!-- end container -->
    </div>
</main>
<jsp:include page="../footer.jsp"/>
<script>
    document.querySelector("form").addEventListener("submit", function (event) {

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

    // $("#clienteSearchInput").autocomplete({
    //     source: function (request, response) {
    //         $.ajax({
    //             url: "../cliente/searchClientiAjax",
    //             datatype: "json",
    //             data: {
    //                 term: request.term,
    //             },
    //             success: function (data) {
    //                 response($.map(data, function (item) {
    //                     return {
    //                         label: item.label,
    //                         value: item.value
    //                     }
    //                 }))
    //             }
    //         })
    //     },
    //     //quando seleziono la voce nel campo deve valorizzarsi la descrizione
    //     focus: function (event, ui) {
    //         $("#clienteSearchInput").val(ui.item.label)
    //         return false
    //     },
    //     minLength: 2,
    //     //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
    //     select: function (event, ui) {
    //         $('#clienteId').val(ui.item.value);
    //         return false;
    //     },
    //     //questo serve in quanto se io imposto un cliente e poi lo cancello
    //     //e faccio altro nella pagina, il valore che poi verrà inviato al
    //     //controller deve essere resettato altrimenti non mi darebbe
    //     //l'errore di validazione di cliente mancante
    //     change: function (event, ui) {
    //         if (!$("#clienteSearchInput").val()) {
    //             $('#clienteId').val('');
    //             return false;
    //         }
    //     }
    // });
</script>
</body>
</html>