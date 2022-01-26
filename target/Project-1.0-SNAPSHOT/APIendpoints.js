/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// You must run the URI... 
// 127.0.0.1:49000/api/library/create ...to create the resources
// prior to running the index.html file 
// Allow Blocked content... if you see that warning in the browser

$(document).ready(function() {
    $.ajax({
        url: "http://localhost:49000/api/library/fetchABook/1"
    }).then(function(data) {
       $('.resp-id').append(data.id);
       $('.resp-isbn').append(data.isbn);
       $('.resp-publisher').append(data.publisher);
    });
});

