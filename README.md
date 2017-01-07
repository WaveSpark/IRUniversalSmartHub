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
   
    Example code string: (Frequency is first value before comma)   code="38000,15,8,8,8,8,19,8,8,7,27,7,14,8,9,6,9,6,9,6,19,8,19,8,14,7,20,7,14,7,14,8,19,8,26,7,3227,15,8,8,8,8,19,8,8,7,27,7,14,8,9,6,9,6,9,6,19,8,19,8,14,7,20,7,14,7,14,8,19,8,26,7,3227,15,8,8,8,8,19,8,8,7,27,7,14,8,9,6,9,6,9,6,19,8,19,8,14,7,20,7,14,7,14,8,19,8,26,7,3227,"
     

    Receive Example:
    
    irSmartHub.receiveIR(ip, textView)
    
    First parameter is the String ip address. Second parameter is the textview you want to populate the learned ir code with. Can be updated to what ever you want. If you want to change then you can edit the onPostExecute method for the receiving AsyncTask.
