<%@page import="Network.*, ResultManager.*,  Functions.*"%>
<%@page import="Utils.*" %>

<html>
<head>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<nav class="navbar">
		<span class="open-slide"> <a href="#" onclick="openSlideMenu()">
				<svg width="35" height="60" id="navigation">
						<path d="M0,5 35,5" stroke="#6db6db" stroke-width="4" />
						<path d="M0,14 35,14" stroke="#fff" stroke-width="4" />
						<path d="M0,23 35,23" stroke="#fff" stroke-width="4" />
						<path d="M0,32 35,32" stroke="#fff" stroke-width="4" />
						<path d="M0,41 35,41" stroke="#fff" stroke-width="4" />
				</svg>
		</a>
		</span>
		<div id="side-menu" class="side-nav">
			<a href="#" class="btn-close" onclick="closeSlideMenu()">&times;</a>
			<a href="index.jsp">Home</a> 
			<a href="About.html">About</a> 
			<a href="HIW.html">How It Works</a>
		</div>
		<div class="title">
			<div class = "neural"><h1>NeuralInvestor</h1>
		</div>
	</nav>
	<script>
			function openSlideMenu() {
				document.getElementById('side-menu').style.width = '150px';
				var x = document.getElementById("navigation");
				x.style.display = "none";
			}
			
			function closeSlideMenu() {
				document.getElementById('side-menu').style.width = '0';
				setTimeout(unhideSlideMenu, 300);
			}
	
			function unhideSlideMenu() {
				var x = document.getElementById("navigation");
				x.style.display = "block";
			}
	
		</script>
	<div class="jumbotron">
		<h1>Considering an Investment?</h1>
		<p>Let's Use Neural Networks to help.</p>
	</div>
	<%-- 
		<% %> <-- One to many lines of java code
		<%= %> <-- One line of java code
		<%! %> <-- Variable or method declaration
	--%>
	<div class="row input-title">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<h1>Calculate the Probability of a Successful Investment!</h1>
		</div>
	</div>
	<div class="row input-fields">
		<div class="col-sm-1"></div>
		<div class="col-sm-4">
			<h2>Company Name</h2>
			<form action = "/NeuralInvestor/index.jsp">
				<input type="text" name="Name">
			<h2>Company Stock Value at Beginning of Chosen Period</h2>
				<input type="text" name="stocki">
			<h2>Company Stock Value at End of Chosen Period</h2>
				<input type="text" name="stockf">
			<h2>Previous Fiscal Quarter Value</h2>
				<input type="text" name="revenuei">
			<h2>Current Fiscal Quarter Value</h2>
				<input type="text" name="revenuef">
			<h2>Functions</h2>
				<input type="radio" name="sig" value="0"> Default: Sigmoid
				<input type="radio" name="tan" value="1"> Hyperbolic Tangent
				<input type="submit" name="submit" value="Submit">
			</form>
		</div>
	
		<%
			// notes down the start time
			long startTime = System.currentTimeMillis();
			
			// tells the user that the program is running
		
			// reads the two text files that contain all the known inputs and respective results
			float[][] knownInputs = FileReader.readSampleData("/Users/siddharth.bhattacharya/Desktop/Hackathon/workspace/NeuralInvestor/data/TestInputs.txt");
			int[] knownResults = FileReader.readSampleResults("/Users/siddharth.bhattacharya/Desktop/Hackathon/workspace/NeuralInvestor/data/KnownResults.txt");
			
			System.out.println(knownInputs);
			String name = request.getParameter("Name");
			String sig = request.getParameter("sig");
			String tan = request.getParameter("tan");
			
			if (name == null) {
				name = " ";
			}
		
			Networking neuralNetwork;
			int function;
			if(tan != null){
				function = 1;
			}
			else{
				function = 0;
			}
			
			// creates the network object that does all the work
			neuralNetwork = new Networking(knownInputs, knownResults, function);
			
			
			// iterations will start and will result in a final adjusted network object
			neuralNetwork.networkLearning();
			
			// displays object information to the user (such as reliability)
			int nodeNumber =  neuralNetwork.getDimension();
			float SuccessPercentage = neuralNetwork.getSuccessPercentage();
			String SP = SuccessPercentage + "%";
			
			String stocki = request.getParameter("stocki");
			String stockf = request.getParameter("stockf");
			
			String revenuei = request.getParameter("revenuei");
			String revenuef = request.getParameter("revenuef");
			
			float stockin, stockfn, percents1, revenuein, revenuefn, percents2;
			float[] valueToPredict; 
			PredictOutput po;
			Calculate analyze;
			int result;
			String MI = " ";
			
			
			
			if (stocki != null){ 
				stockin = Float.parseFloat(stocki);
				stockfn = Float.parseFloat(stockf);
				
				percents1 = ((stockfn-stockin)/stockin)/100;
				
				 revenuein = Float.parseFloat(revenuei);
				 revenuefn = Float.parseFloat(revenuef);
				
				 percents2 = ((revenuefn-revenuein)/revenuein)/100;
				
				// this is where the user can input what values he or she wants to test
				valueToPredict = new float[] { percents1, percents2 };
				
				// creates new PredictOutput object in order to calculate if the user prediction will be 1 or 0 
				 po = new PredictOutput();
				 analyze = neuralNetwork.getCalculate();
				 result = po.prediction(analyze, valueToPredict, function);
				 if (result == 1){
						MI = "Data says YES"; 
					}
					else { 
						MI = "Data says NO"; 
					}
			}
			
			
			// notes down the end time
			long endTime = System.currentTimeMillis();
			long ExecutionTime = endTime-startTime;
		%>
		<div class="col-sm-6 output">
		<h3>Company Name:</h3>
		<div class = "compname"><%= name%></div>
		<br><br><br><br><br><br>
		<h3>Success Percentage:</h3>
		<div class= "success"><%= SP%></div>
		<br><br><br><br><br><br>
		<h3>Make Investment?</h3>
		<div class = "invest"><%= MI%></div>
	</div>
	<div class="col-sm-1"></div>
	</div>
</body>
</html>