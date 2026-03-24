<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
    <jsp:include page="../header.jsp"/>
    <title>Statistiche Ordini</title>
</head>

<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp"/>

<main class="flex-shrink-0">
    <div class="container">

        <div class="card">
            <div class="card-header bg-warning">
                <h5>Calcolo Statistiche</h5>
            </div>

            <div class="card-body">

                <form method="post" action="${pageContext.request.contextPath}/ordine/stats"
                      class="row g-3">

                    <div class="col-md-4">
                        <label class="form-label">Data Inizio</label>
                        <input type="date" name="dataInizio" class="form-control">
                        <small class="text-muted">Lascia vuoto per includere tutti i dati precedenti alla data di fine</small>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Data Fine</label>
                        <input type="date" name="dataFine" class="form-control">
                        <small class="text-muted">Lascia vuoto per includere tutti i dati successivi alla data di inizio</small>
                    </div>

                    <div class="col-md-4 mt-4 d-flex align-items-end justify-content-end">
                        <button type="submit" class="btn btn-outline-info">
                            Visualizza
                        </button>
                    </div>
                </form>

            </div>
        </div>

    </div>
</main>

<jsp:include page="../footer.jsp"/>
<script>
    const form = document.querySelector('form[name="statsForm"]') || document.querySelector('form');

    form.addEventListener('submit', function (e) {
        const dataInizio = form.querySelector('input[name="dataInizio"]').value;
        const dataFine = form.querySelector('input[name="dataFine"]').value;

        if (dataInizio && dataFine) { // se entrambe sono valorizzate
            if (new Date(dataFine) < new Date(dataInizio)) {
                alert("La data di fine deve essere uguale o successiva alla data di inizio.");
                e.preventDefault(); // blocca il submit
            }
        }
    });
</script>
</body>
</html>