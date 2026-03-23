<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp"/>
    <title>Visualizza Ordine</title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp"/>

<main class="flex-shrink-0">
    <div class="container">

        <div class='card'>
            <div class='card-header'>
                Dettaglio Ordine
            </div>

            <div class='card-body'>
                <!-- Ordine base -->
                <dl class="row">
                    <dt class="col-sm-3 text-right">Id Ordine:</dt>
                    <dd class="col-sm-9">${show_ordine_attr.id}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Codice:</dt>
                    <dd class="col-sm-9">${show_ordine_attr.codice}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Data Ordine:</dt>
                    <dd class="col-sm-9">
                        <fmt:formatDate value="${show_ordine_attr.dataOrdineDate}" pattern="dd/MM/yyyy HH:mm"/>
                    </dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Chiuso:</dt>
                    <dd class="col-sm-9">
                        ${show_ordine_attr.closed ? 'Si' : 'No'}
                    </dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Costo Totale:</dt>
                    <dd class="col-sm-9">${show_ordine_attr.costoTotale} euro</dd>
                </dl>

                <!-- Bottone per espandere dettagli -->
                <button class="btn btn-outline-info mb-3" type="button" data-bs-toggle="collapse"
                        data-bs-target="#dettagliOrdine" aria-expanded="false" aria-controls="dettagliOrdine">
                    Mostra dettagli
                </button>

                <!-- Sezione collapsable -->
                <div class="collapse" id="dettagliOrdine">
                    <div class="card card-body">
                        <!-- Cliente -->
                        <h6>Cliente</h6>
                        <dl class="row">
                            <dt class="col-sm-3 text-right">Nome:</dt>
                            <dd class="col-sm-9">${show_ordine_attr.nomeCliente}</dd>

                            <dt class="col-sm-3 text-right">Id Cliente:</dt>
                            <dd class="col-sm-9">${show_ordine_attr.clienteId}</dd>
                        </dl>

                        <!-- Pizze -->
                        <h6>Pizze ordinate</h6>
                        <ul>
                            <c:forEach items="${show_ordine_attr.pizzeDescrizione}" var="p">
                                <li>${p}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <!-- Footer con pulsante Back -->
                <div class='card-footer mt-3'>
                    <a href="${pageContext.request.contextPath }/ordine" class='btn btn-outline-secondary'>
                        <i class='fa fa-chevron-left'></i> Back
                    </a>
                </div>
            </div>
        </div>

    </div>
</main>

<jsp:include page="../footer.jsp"/>
</body>
</html>