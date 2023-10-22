<!doctype html>
<html lang="en">
<head>
  <title>Login</title>
  <jsp:include page="../components/head.jsp" />
</head>

<body>
<div id="layout-wrapper">
  <jsp:include page="../components/navbar.jsp" />
  <jsp:include page="../components/sidebar.jsp" />

  <div class="main-content">

    <div class="page-content">
      <div class="container-fluid">

        <!-- start page title -->
        <div class="row">
          <div class="col-12">
            <div class="page-title-box d-sm-flex align-items-center justify-content-between">
              <h4 class="mb-sm-0 font-size-18">Starter Page</h4>

              <div class="page-title-right">
                <ol class="breadcrumb m-0">
                  <li class="breadcrumb-item"><a href="javascript: void(0);">Pages</a></li>
                  <li class="breadcrumb-item active">Starter Page</li>
                </ol>
              </div>

            </div>
          </div>
        </div>
        <!-- end page title -->

      </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->


    <jsp:include page="../components/footer.jsp" />

  </div>


</div>

<jsp:include page="../components/rightSidebar.jsp" />

<jsp:include page="../components/scripts.jsp" />
<!-- pace js -->

</body>
</html>



