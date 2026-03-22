<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>

    <!-- Common imports in pages -->
    <jsp:include page="../header.jsp" />

    <title>Lista Ordini</title>
</head>

<body class="d-flex flex-column h-100">

<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp"></jsp:include>

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <!-- Alert Messaggi -->
        <div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none':''}" role="alert">
            ${successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':''}" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>

        <div class="card">
            <div class="card-header">
                <h5>Lista Ordini</h5>
            </div>
            <div class="card-body">
                <a class="btn btn-warning mb-3" href="${pageContext.request.contextPath}/ordine/insert">Aggiungi Ordine</a>

                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Codice</th>
                            <th>Cliente</th>
                            <th>Pizze</th>
                            <th>Prezzo Totale</th>
                            <th>Azioni</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ordine_list_attribute}" var="ordineItem">
                            <tr>
                                <td>${ordineItem.codice}</td>
                                <td>${ordineItem.nomeCliente}</td>
                                <td>
                                    <c:forEach items="${ordineItem.pizzeDescrizione}" var="pizza" varStatus="st">
                                        ${pizzeDescrizione}<c:if test="${!st.last}">, </c:if>
                                    </c:forEach>
                                </td>
                                <td>€ ${ordineItem.costoTotale}</td>
                                <td>
                                    <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/ordine/edit/${ordineItem.id}">Modifica</a>
                                    <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/ordine/delete/${ordineItem.id}">Elimina</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty ordine_list_attribute}">
                            <tr>
                                <td colspan="5" class="text-center">Nessun ordine trovato</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>

                <!-- end card-body -->
            </div>
            <!-- end card -->
        </div>

        <!-- end container -->
    </div>

</main>

<!-- Footer -->
<jsp:include page="../footer.jsp" />

</body>
</html>