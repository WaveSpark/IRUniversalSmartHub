# IRUniversalSmartHub - Android Integration
Integrate the IR Universal Smart Hub into your Android project.

Steps:
# 1) Copy
    Copy the IRUniversalSmartHub.java file into your project
# 2) Declare
    IRUniversalSmartHub irSmartHub;
# 3) Initialize
    irSmartHub = new IRUniversalSmartHub(context);
# 4) Use
    Send Example: 
    
    irSmartHub.sendIR(ipAddress, code)
    
    Note: Both parameters are strings.
    
    
    You can use irSmartHub.getIpList(); to get get list of hubs connected to the network. (You will need to update the AsyncTask onPostExecute method to do something with the list of IP Addresses.)
   
    Example code string (Samsung, TV, Power On): (Frequency is first value before comma)   code="38000,1,1,343,172,21,22,21,22,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,65,21,65,21,65,21,65,21,65,21,22,21,22,21,22,21,65,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,65,21,65,21,65,21,65,21,4923"
     

    Receive Example:
    
    irSmartHub.receiveIR(ip, textView)
    
    First parameter is the String ip address. Second parameter is the textview you want to populate the learned ir code with. Can be updated to what ever you want. If you want to change then you can edit the onPostExecute method for the receiving AsyncTask.
