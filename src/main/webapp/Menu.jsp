<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 <!--begin of menu-->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home">Shoes</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">
	                    <c:if test="${sessionScope.accountSession.isAdmin == 1}">
							<li class="nav-item">
	                            <a class="nav-link" href="managerAccount">Manager Account</a>
	                        </li>                    
	                    </c:if>
                    
                        <c:if test="${sessionScope.accountSession.isSell == 1}">
	                        <li class="nav-item">
	                            <a class="nav-link" href="managerProduct">Manager Product</a>
	                        </li>         
                        </c:if>
                                   
                       <c:if test="${sessionScope.accountSession != null}">
	                       	<li class="nav-item">
	                            <a class="nav-link" href="#">Hello ${sessionScope.accountSession.user}</a>
	                        </li>
	                       	 <li class="nav-item">
	                            <a class="nav-link" href="logout">Logout</a>
	                        </li>
                       </c:if>
                       
                      <c:if test="${sessionScope.accountSession == null}">
	                      <li class="nav-item">
	                            <a class="nav-link" href="Login.jsp">Login</a>
	                        </li> 
                      </c:if>
                        
                    </ul>

                    <form action="search" method="post" class="form-inline my-2 my-lg-0">
                        <div class="input-group input-group-sm">
                            <input oninput="searchByName(this)" value ="${values}"  name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <a class="btn btn-success btn-sm ml-3" href="Cart.jsp">
                            <i class="fa fa-shopping-cart"></i> Cart
                            <span class="badge badge-light">${sessionScope.list.size()}</span>
                        </a>
                    </form>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Siêu thị giày chất lượng cao</h1>
                <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp các sản phầm giày nhập từ Trung Quốc</p>
            </div>
        </section>
        <!--end of menu-->