<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="it" class="h-100">
<head>

    <!-- Common imports in pages -->
    <jsp:include page="./header.jsp"/>
    <!-- Custom styles per le features di bootstrap 'Columns with icons' -->
    <link href="${pageContext.request.contextPath}/assets/css/features.css" rel="stylesheet">

    <title>gestione pizzeria</title>
</head>
<body class="d-flex flex-column h-100">

<!-- #####################################  -->
<!-- elementi grafici per le features in basso  -->
<!-- #####################################  -->
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="people-circle" viewBox="0 0 16 16">
        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
        <path fill-rule="evenodd"
              d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
    </symbol>
    <symbol id="collection" viewBox="0 0 16 16">
        <path d="M2.5 3.5a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1h-11zm2-2a.5.5 0 0 1 0-1h7a.5.5 0 0 1 0 1h-7zM0 13a1.5 1.5 0 0 0 1.5 1.5h13A1.5 1.5 0 0 0 16 13V6a1.5 1.5 0 0 0-1.5-1.5h-13A1.5 1.5 0 0 0 0 6v7zm1.5.5A.5.5 0 0 1 1 13V6a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5h-13z"/>
    </symbol>
    <symbol id="toggles2" viewBox="0 0 16 16">
        <path d="M9.465 10H12a2 2 0 1 1 0 4H9.465c.34-.588.535-1.271.535-2 0-.729-.195-1.412-.535-2z"/>
        <path d="M6 15a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm0 1a4 4 0 1 1 0-8 4 4 0 0 1 0 8zm.535-10a3.975 3.975 0 0 1-.409-1H4a1 1 0 0 1 0-2h2.126c.091-.355.23-.69.41-1H4a2 2 0 1 0 0 4h2.535z"/>
        <path d="M14 4a4 4 0 1 1-8 0 4 4 0 0 1 8 0z"/>
    </symbol>
    <symbol id="chevron-right" viewBox="0 0 16 16">
        <path fill-rule="evenodd"
              d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
    </symbol>
</svg>
<!-- ############## end ###################  -->


<!-- Fixed navbar -->
<jsp:include page="./navbar.jsp"></jsp:include>


<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>

        <div class="p-5 mb-4 bg-warning rounded-3">
            <div class="container-fluid py-5">
                <h1 class="display-5 fw-bold text-dark">Benvenuto alla Gestione pizze</h1>
                <p class="col-md-8 fs-4 text-dark">Dal 1950, solo <b>vera</b> pizza napoletana</p>
                <a class="btn btn-outline-dark btn-lg" href="${pageContext.request.contextPath}/pizza/search">Vai a
                    Ricerca Pizze</a>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-12">
            <div id="caroselloPizze" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#caroselloPizze" data-bs-slide-to="0" class="active"></button>
                    <button type="button" data-bs-target="#caroselloPizze" data-bs-slide-to="1"></button>
                    <button type="button" data-bs-target="#caroselloPizze" data-bs-slide-to="2"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block mx-auto" style="width:70%;" src="${pageContext.request.contextPath}/assets/img/favicons/pizzaCarosello1.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block mx-auto" style="width:70%;" src="${pageContext.request.contextPath}/assets/img/favicons/pizzaCarosello2.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block mx-auto" style="width:70%;" src="${pageContext.request.contextPath}/assets/img/favicons/pizzaCarosello3.avif" alt="Third slide">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#caroselloPizze" data-bs-slide="prev">
                    <span class="sr-only">Precedente</span>
                </button>
                <button class="carousel-control-next" data-bs-target="#caroselloPizze" data-bs-slide="next">
                    <span class="sr-only">Successiva</span>
                </button>
            </div>
        </div>
    </div>
    <style>
        .carousel-control-prev,
        .carousel-control-next {
            width: 80px;
            height: 80px;
            top: 50%;
            transform: translateY(-50%);
            background-color: var(--bs-warning);
            border-radius: 50%;
            color: var(--bs-dark);
        }

        .carousel-control-prev {
            left: 30px;

        }

        .carousel-control-next {
            right: 30px;
        }

        .carousel-item {
            text-align: center;
        }

        .carousel-item img {
            max-height: 400px;
            object-fit: cover;
        }
    </style>

    <!--  features di bootstrap 'Columns with icons'  -->
    <div class="container px-4 py-5" id="featured-3">
        <div class="row g-4 py-5 row-cols-1 row-cols-lg-4">
            <div class="feature col">
                <div class="feature-icon rounded-circle bg-dark bg-gradient">
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#collection"/>
                    </svg>
                </div>
                <h2>Ricerca Clienti</h2>
                <p>Visualizza la lista dei clienti</p>
                <a href="${pageContext.request.contextPath}/cliente/search" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#chevron-right"/>
                    </svg>
                </a>
            </div>
            <div class="feature col">
                <div class="feature-icon rounded-circle bg-dark bg-gradient">
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#people-circle"/>
                    </svg>
                </div>
                <h2>Inserisci Nuovo Cliente</h2>
                <p>Inserisci un nuovo cliente nella base dati</p>
                <a href="${pageContext.request.contextPath}/cliente/insert" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#chevron-right"/>
                    </svg>
                </a>
            </div>
            <div class="feature col">
                <div class="feature-icon rounded-circle bg-dark bg-gradient">
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#toggles2"/>
                    </svg>
                </div>
                <h2>Ricerca Pizza</h2>
                <p>Cerca una pizza specifica per vedere se è presente sul sito e disponibile</p>
                <a href="${pageContext.request.contextPath}/pizza/search" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#chevron-right"/>
                    </svg>
                </a>
            </div>
            <div class="feature col">
                <div class="feature-icon rounded-circle bg-dark bg-gradient">
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#people-circle"/>
                    </svg>
                </div>
                <h2>Inserisci Nuova Pizza</h2>
                <p>Inserisci una nuova pizza per aggiornare il catalogo delle pizze</p>
                <a href="${pageContext.request.contextPath}/pizza/insert" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#chevron-right"/>
                    </svg>
                </a>
            </div>
            <div class="feature col">
                <div class="feature-icon rounded-circle bg-dark bg-gradient">
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#toggles2"/>
                    </svg>
                </div>
                <h2>Ricerca Ordine</h2>
                <p>Cerca un ordine sul sito</p>
                <a href="${pageContext.request.contextPath}/ordine/search" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#chevron-right"/>
                    </svg>
                </a>
            </div>
            <div class="feature col">
                <div class="feature-icon rounded-circle bg-dark bg-gradient">
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#people-circle"/>
                    </svg>
                </div>
                <h2>Inserisci Nuovo Ordine</h2>
                <p>Inserisci una nuovo ordine per aggiornare il catalogo degli ordini</p>
                <a href="${pageContext.request.contextPath}/pizza/insert" class="icon-link">
                    Vai alla funzionalità
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#chevron-right"/>
                    </svg>
                </a>
            </div>
            <div class="feature col">
                <div class="feature-icon rounded-circle bg-dark bg-gradient">
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#people-circle"/>
                    </svg>
                </div>
                <h2>Vedi tutti i clienti</h2>
                <p>Consulta la lista dei clienti</p>
                <a href="${pageContext.request.contextPath}/cliente" class="icon-link">
                    Vai alla lista
                    <svg class="bi bg-warning rounded-circle" width="1em" height="1em">
                        <use xlink:href="#chevron-right"/>
                    </svg>
                </a>
            </div>
        </div>
    </div>

</main>
<script>
    var carosello = document.querySelector('#caroselloPizze');
    var carousel = new bootstrap.Carousel(carosello, {
        interval: 6000,   // tempo in ms tra le slide
        ride: 'carousel',  // autoplay
        pause: false,      // non fermare l'autoplay quando l'utente interagisce
        wrap: true         // torna alla prima slide dopo l'ultima
    });
</script>

<!-- Footer -->
<jsp:include page="./footer.jsp"/>
</body>
</html>