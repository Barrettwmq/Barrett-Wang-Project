/**
 * 
 */
document.addEventListener("DOMContentLoaded", function(){
    document.getElementById("login").addEventListener("submit", function(event){
        event.preventDefault();
        let username = document.getElementById('username').value;
        let password = document.getElementById('password').value;
                
        let info = {
            'username': username,
            'password': password
        };
                
        fetch('LoginServlet',{
            method: 'POST',
            body: JSON.stringify(info)
        })
        .then(response => response.json())
        .then(data => {
            Login(data);
        })
        .catch(function(error){
            console.log(error);
        })
    })
})
        
function Login(data){
    if(data == 'User logged in'){
        window.location.href = 'home.html';
    }
            
    else{
        document.getElementById('error').style.display = 'block';
        document.getElementById('error').style.color = 'white';
        document.getElementById('error').style.fontSize = '25px';
        document.getElementById('error').innerHTML = data;
    }
}