<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
              <h4 class="mb-sm-0 font-size-18">User List</h4>

              <div class="page-title-right">
                <ol class="breadcrumb m-0">
                  <li class="breadcrumb-item"><a href="javascript: void(0);">Contact</a></li>
                  <li class="breadcrumb-item active">User List</li>
                </ol>
              </div>

            </div>
          </div>
        </div>
        <!-- end page title -->

        <div class="row align-items-center">
          <div class="col-md-6">
            <div class="mb-3">
              <h5 class="card-title">Contact List <span class="text-muted fw-normal ms-2">(834)</span></h5>
            </div>
          </div>

          <div class="col-md-6">
            <div class="d-flex flex-wrap align-items-center justify-content-end gap-2 mb-3">
              <div>
                <ul class="nav nav-pills">
                  <li class="nav-item">
                    <a class="nav-link active" href="apps-contacts-list.html" data-bs-toggle="tooltip" data-bs-placement="top" title="List"><i class="bx bx-list-ul"></i></a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="apps-contacts-grid.html" data-bs-toggle="tooltip" data-bs-placement="top" title="Grid"><i class="bx bx-grid-alt"></i></a>
                  </li>
                </ul>
              </div>
              <div>
                <a href="#" class="btn btn-light"><i class="bx bx-plus me-1"></i> Add New</a>
              </div>

              <div class="dropdown">
                <a class="btn btn-link text-muted py-1 font-size-16 shadow-none dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  <i class="bx bx-dots-horizontal-rounded"></i>
                </a>

                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item" href="#">Action</a></li>
                  <li><a class="dropdown-item" href="#">Another action</a></li>
                  <li><a class="dropdown-item" href="#">Something else here</a></li>
                </ul>
              </div>
            </div>

          </div>
        </div>
        <!-- end row -->

        <div class="table-responsive mb-4">
          <table class="table align-middle datatable dt-responsive table-check nowrap" style="border-collapse: collapse; border-spacing: 0 8px; width: 100%;">
            <thead>
            <tr>
              <th scope="col" style="width: 50px;">
                <div class="form-check font-size-16">
                  <input type="checkbox" class="form-check-input" id="checkAll">
                  <label class="form-check-label" for="checkAll"></label>
                </div>
              </th>
              <th scope="col">Name</th>
              <th scope="col">Position</th>
              <th scope="col">Email</th>
              <th scope="col">Hiring Date</th>
              <th scope="col">Role</th>
              <th style="width: 80px; min-width: 80px;">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">

              <fmt:formatDate value="${user.hireDate}" pattern="yyyy-MM-dd" var="formattedDate" />

              <c:choose>
                <c:when test="${user.role.id == 1}">
                  <c:set var="roleName" value="Admin" />
                </c:when>
                <c:when test="${user.role.id == 2}">
                  <c:set var="roleName" value="Employee" />
                </c:when>
                <c:when test="${user.role.id == 3}">
                  <c:set var="roleName" value="Technicien" />
                </c:when>
                <c:otherwise>
                  <c:set var="roleName" value="Unknown Role" />
                </c:otherwise>
              </c:choose>


              <tr>
              <th scope="row">
                <div class="form-check font-size-16">
                  <input type="checkbox" class="form-check-input" id="contacusercheck11">
                  <label class="form-check-label" for="contacusercheck11"></label>
                </div>
              </th>
              <td>
                <img src="resources/images/users/avatar-2.jpg" alt="" class="avatar-sm rounded-circle me-2">
                <a href="#" class="text-body">${user.name}</a>
              </td>
              <td>${user.position}</td>
              <td>${user.email}</td>
              <td>${formattedDate}</td>
              <td>${roleName}</td>
              <td>
                <div class="dropdown">

                  <form action="${pageContext.request.contextPath}/deleteUser" method="get">
                    <input type="hidden" name="userId" value="${user.id}" />
                  <button type="submit" class="btn btn-soft-danger waves-effect waves-light"><i class="bx bx-block font-size-16 align-middle"></i></button>
                  </form>
                    <button type="button"   class="btn btn-soft-warning waves-effect waves-light"><i class="bx bx-pencil font-size-16 align-middle"></i></button>

                </div>
              </td>
            </tr>
            </c:forEach>
            </tbody>
          </table>
          <!-- end table -->
        </div>
        <!-- end table responsive -->

      </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->


  </div>


</div>

<jsp:include page="../components/rightSidebar.jsp" />

<jsp:include page="../components/scripts.jsp" />
<!-- pace js -->

</body>
</html>



