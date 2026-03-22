<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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

                <form method="post" action="${pageContext.request.contextPath}/pizza/list"
                      class="row g-3">

                    <div class="col-md-4">
                        <label for="descrizione" class="form-label">Descrizione</label>
                        <input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" >
                    </div>

                    <div class="col-md-4">
                        <label for="ingredienti" class="form-label">Ingredienti</label>
                        <input type="text" name="ingredienti" id="ingredienti" class="form-control" placeholder="Inserire gli ingredienti" >
                    </div>

                    <div class="col-md-4">
                        <label for="prezzoBase" class="form-label">Prezzo</label>
                        <input type="number" step="0.01" name="prezzoBase" id="prezzoBase" class="form-control" placeholder="Inserire il prezzo" >
                    </div>

                    <div class="col-12">
                        <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
                        <input class="btn btn-warning" type="reset" value="Ripulisci">
                        <a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath }/pizza/insert">Add New</a>
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