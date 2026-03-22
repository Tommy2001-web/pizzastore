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
        <spring:hasBindErrors  name="pizza_regista_attr">
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


                <form:form method="post" modelAttribute="insert_pizza_attr" action="save" novalidate="novalidate" class="row g-3">

                    <div class="col-md-6">
                        <label for="descrizione" class="form-label">Descrizione <span class="text-danger">*</span></label>
                        <spring:bind path="descrizione">
                            <input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il descrizione" value="${insert_pizza_attr.descrizione }">
                        </spring:bind>
                        <form:errors  path="descrizione" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="ingredienti" class="form-label">Ingredienti <span class="text-danger">*</span></label>
                        <spring:bind path="ingredienti">
                            <input type="text" name="ingredienti" id="ingredienti" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il ingredienti" value="${insert_pizza_attr.ingredienti }">
                        </spring:bind>
                        <form:errors  path="ingredienti" cssClass="error_field" />
                    </div>

                    <div class="col-md-6">
                        <label for="prezzoBase" class="form-label">Prezzo Base <span class="text-danger">*</span></label>
                        <spring:bind path="prezzoBase">
                            <input type="number" name="prezzoBase" id="prezzoBase" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire l'prezzoBase" value="${insert_pizza_attr.prezzoBase }">
                        </spring:bind>
                        <form:errors  path="prezzoBase" cssClass="error_field" />
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
</body>
</html>