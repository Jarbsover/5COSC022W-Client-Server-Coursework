w2115801
Jia Bei Lu
Client-Server Coursework Report

- Question: In your report, explain the default lifecycle of a JAX-RS Resource class. Is a new instance instantiated for every incoming request, or does the runtime treat it as asingleton? Elaborate on how this architectural decision impacts the way you manage and synchronize your in-memory data structures (maps/lists) to prevent data loss or race conditions.

A JAX-RS resource class is a per-request life cycle, so for every HTTP request is made, a new instance of the JAX-RS resource class is also made at the same time.
Creating a new instance means that each resource class is independent from each other, so there is no danger of concurrent threads interfering with each other.
For in memory data structures, since each new instance doesn’t last forever, data would be lost if we stored it in instance variables. To fix this we need to use static variables or manage it in a separate class.
Concurrent data structures means that these are accessed by multiple requests at the same time, so there is a danger of having inconsistent data, and having errors. To try to fix this we should use thread safe structures or use synchronisation techniques.
The per-request cycle improves safety by being independent, but on the other side needs to keep an eye on concurrency issues related to the shared memory data.


- Question: Why is the provision of ”Hypermedia” (links and navigation within responses) considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach benefit client developers compared to static documentation?

Hypermedia is important because it allows clients to easily navigate between all resources and actions through the links given, having these links makes it easier for clients to understand what is happening, and makes it easier to follow.
So it means that clients don’t need to know the endpoints, if links are updated it doesn’t break anything which also makes it easier to maintain.
It makes everything flexible compared to static designs.


- Question: When returning a list of rooms, what are the implications of returning only
IDs versus returning the full room objects? Consider network bandwidth and client side
processing.

Returning only room IDs optimizes network bandwidth as you aren’t returning the full object which takes longer to load and has a larger data size. 
For client side processing only returning the IDs allows the client to make follow up requests for specific IDs, like getting more details which is more efficient than loading the full objects all at once.


- Question: Is the DELETE operation idempotent in your implementation? Provide a detailed
justification by describing what happens if a client mistakenly sends the exact same DELETE
request for a room multiple times.

When a client requests a room to be deleted, for example: DELETE …/api/rooms/G.100, and it is sent to the server, after it being deleted, it will respond that if it did exist a ‘200 OK’.
If the client sends the same request for the same room (G.100) to be deleted, it will be sent to the server, it will check again and the server will see that the room is deleted, so nothing in the server changes.


- Question: We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on
the POST method. Explain the technical consequences if a client attempts to send data in
a different format, such as text/plain or application/xml. How does JAX-RS handle this
mismatch?

If the client sends data in the incorrect format, the output will be a ‘HTTP 415 Unsupported Media Type’ error, which means that we are reaching the server however, 
it can’t process the data in this format, this happens if we try to send data that’s not in this example, in a json format.
JAX-RS uses the MediaType header of the request to see what type of data is used. If the type of data isn’t matched, the request can’t be done.


- Question: You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., /api/vl/sensors/type/CO2). Why
is the query parameter approach generally considered superior for filtering and searching
collections?

Query parameters are generally more preferred over the pathing method because it allows us to filter directly from the resource we asked for, rather than trying to path from the specific sensor itself.
For example, if we tried to find an ‘O2’ sensor, query parameters would make it look like this: ‘sensors?type=O2’, this allows it to directly filter the O2 sensor, 
rather than ‘sensors/type/O2’ which is trying to path the ‘type’ variable and trying to find a distinct O2 sensor.


- Question: Discuss the architectural benefits of the Sub-Resource Locator pattern. How
does delegating logic to separate classes help manage complexity in large APIs compared
to defining every nested path (e.g., sensors/{id}/readings/{rid}) in one massive controller class?

It allows for modularity, keeping classes separate is less daunting and is easier to navigate than just having one massive class. 
Having one massive class also increases the danger of mixing around variables and logic when you want to try to keep them as separate as possible, which is just localizing.
Changes in a sub resource class where everything is separated is also a lot safer to do than one larger class, and each sub resource class can be tested isolated from one another, errors are easier to fix too.


- Question: Why is HTTP 422 often considered more semantically accurate than a standard
404 when the issue is a missing reference inside a valid JSON payload?

A HTTP 422 error means that the request was correct in terms of if it was in a valid JSON payload and how it was structured, it just doesn’t work semantically, 
in other words, the request is trying to find something that doesn’t exist. All you need to change is the data.

A HTTP 404 error means that the endpoint or URI is completely wrong, but in this example of a valid JSON payload, that wouldn’t be true. It would be indicating that the wrong things needed to be changed to the request.


- Question: From a cybersecurity standpoint, explain the risks associated with exposing
internal Java stack traces to external API consumers. What specific information could an
attacker gather from such a trace?

Information the clients could gain from exposing the internal Java stack traces would be the structure of the code, like package names, class and method names. 
This helps an attacker map out how the requests are processed and craft attacks after understanding the structure.
They can also find file system paths and environment info, like the OS type. This can help attackers path attacks and understand where sensitive files may be.
A stack trace basically gives attackers a blueprint of your code, showing sensitive data, structure and the technology that is used.


- Question: Why is it advantageous to use JAX-RS filters for cross-cutting concerns like
logging, rather than manually inserting Logger.info() statements inside every single resource method?

Logger.info() statements mean that you have to log each statement everytime, which is repetitive.
