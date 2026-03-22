<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>

    <!-- Common imports in pages -->
    <jsp:include page="../header.jsp" />

    <title>Pagina dei Risultati</title>
</head>

<body class="d-flex flex-column h-100">

<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp"></jsp:include>


<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
            ${successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>
        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>



        <div class='card'>
            <div class='card-header'>
                <h5>Lista dei risultati</h5>
            </div>
            <div class='card-body'>
                <a class="btn btn-warning " href="${pageContext.request.contextPath}/cliente/insert">Add New</a>

                <div class='table-responsive'>
                    <table class='table table-striped ' >
                        <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Cognome</th>
                            <th>Indirizzo</th>
                            <th>Attivo</th>
                            <th>Azioni</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cliente_list_attribute }" var="clienteItem">
                            <tr>
                                <td>${clienteItem.nome }</td>
                                <td>${clienteItem.cognome }</td>
                                <td>${clienteItem.indirizzo }</td>
                                <td>${clienteItem.attivo }</td>
                                <td>
                                    <a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/cliente/show/${clienteItem.id }">Visualizza</a>
                                    <a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/cliente/edit/${clienteItem.id }">Edit</a>
                                    <a class="btn btn-outline-danger btn-sm" href="laservletperrimuovere">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
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