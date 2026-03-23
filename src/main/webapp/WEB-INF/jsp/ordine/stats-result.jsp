<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp"/>
    <title>Risultati Statistiche</title>
</head>

<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp"/>

<main class="flex-shrink-0">
    <div class="container">

        <div class="card">
            <div class="card-header bg-success">
                <h5>Risultati Statistiche</h5>
            </div>

            <div class="card-body">

                <!-- RIEPILOGO -->
                <p><strong>Ricavi totali:</strong> ${stats.ricaviTotali} euro</p>
                <p><strong>Costi totali:</strong> ${stats.costiTotali} euro</p>
                <p><strong>Numero ordini:</strong> ${stats.numeroOrdini}</p>
                <p><strong>Numero pizze:</strong> ${stats.numeroPizze}</p>

                <!-- CLIENTI VIRTUOSI -->
                <h5 class="mt-4">Clienti virtuosi (ordini > 100 euro)</h5>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${stats.clientiVirtuosi}" var="c">
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.nome}</td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty stats.clientiVirtuosi}">
                        <tr>
                            <td colspan="2" class="text-center">
                                Nessun cliente virtuoso trovato
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

            </div>

            <!-- BACK -->
            <div class="card-footer">
                <a href="${pageContext.request.contextPath}/ordine/stats"
                   class="btn btn-outline-secondary">
                    Torna alla ricerca
                </a>
            </div>

        </div>

    </div>
</main>

<jsp:include page="../footer.jsp"/>
</body>
</html>