<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <!-- Common imports in pages -->
    <jsp:include page="../header.jsp"/>
    <title>Visualizza elemento</title>

</head>
<body class="d-flex flex-column h-100">
<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp"/>

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <div class='card'>
            <div class='card-header'>
                Visualizza dettaglio
            </div>

            <div class='card-body'>
                <dl class="row">
                    <dt class="col-sm-3 text-right">Id:</dt>
                    <dd class="col-sm-9">${show_pizza_attr.id}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Descrizione:</dt>
                    <dd class="col-sm-9">${show_pizza_attr.descrizione}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Ingredienti:</dt>
                    <dd class="col-sm-9">${show_pizza_attr.ingredienti}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Prezzo:</dt>
                    <dd class="col-sm-9">${show_pizza_attr.prezzoBase}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Attivo:</dt>
                    <dd class="col-sm-9">${show_pizza_attr.attivo}</dd>
                </dl>


                <div class='card-footer'>
                    <a href="${pageContext.request.contextPath }/pizza" class='btn btn-outline-secondary'
                       style='width:80px'>
                        <i class='fa fa-chevron-left'></i> Back
                    </a>
                </div>
                <!-- end card -->
            </div>

            <!-- end container -->
        </div>
    </div>
</main>
<jsp:include page="../footer.jsp"/>

</body>
</html>