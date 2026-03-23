<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp" />
    <title>Ricerca</title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp" />

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>

        <div class='card'>
            <div class='card-header bg-warning'>
                <h5>Ricerca elementi</h5>
            </div>
            <div class='card-body'>

                <form method="post" action="${pageContext.request.contextPath}/ordine/list"
                      class="row g-3">

                    <div class="col-md-4">
                        <label for="codice" class="form-label">Codice</label>
                        <input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" >
                    </div>

                    <!-- CLIENTE -->
                    <div class="col-md-4">
                        <label class="form-label">Cliente</label>
                        <select class="form-select" name="clienteId">
                            <option value="">- Seleziona Cliente -</option>
                            <c:forEach items="${cliente_list}" var="c">
                                <option value="${c.id}">${c.nome}</option>
                            </c:forEach>
                        </select>
                    </div>

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
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="error_field" id="pizzaError" style="display:none;">Seleziona almeno una pizza!</div>
                        <form:errors path="pizzaIds" cssClass="error_field" />
                    </div>

                    <div class="col-12">
                        <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
                        <input class="btn btn-warning" type="reset" value="Ripulisci">
                        <a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath }/ordine/insert">Add New</a>
                    </div>

                </form>



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