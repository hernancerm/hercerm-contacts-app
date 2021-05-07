# API documentation

<!-- TOC -->

- [Endpoints](#endpoints)
  - [GET /api/contacts](#get-apicontacts)
    - [Request parameters](#request-parameters)
    - [Response](#response)
  - [GET /api/contacts/{contactId}](#get-apicontactscontactid)
    - [Request parameters](#request-parameters-1)
    - [Response](#response-1)
  - [POST /api/contact/](#post-apicontact)
    - [Request body](#request-body)
    - [Response](#response-2)
  - [PUT /api/contacts/{contactId}](#put-apicontactscontactid)
    - [Request parameters](#request-parameters-2)
    - [Request body](#request-body-1)
    - [Response](#response-3)
  - [DELETE /api/contact/{contactId}](#delete-apicontactcontactid)
    - [Request parameters](#request-parameters-3)
    - [Response](#response-4)
- [Entities](#entities)
  - [Requests to server](#requests-to-server)
    - [ContactBase](#contactbase)
  - [Responses by server](#responses-by-server)
    - [ContactPublic](#contactpublic)

<!-- /TOC -->

<a id="markdown-endpoints" name="endpoints"></a>
## Endpoints

<a id="markdown-get-apicontacts" name="get-apicontacts"></a>
### GET /api/contacts

Get a paginated list of contacts, with pagination information.

<a id="markdown-request-parameters" name="request-parameters"></a>
#### Request parameters

|Name|Description|Type|Data type|
|---|---|---|---|
|`page`|Number of page (starts at 0)|query|number|
|`size`|Number of contacts per page|query|number|

<a id="markdown-response" name="response"></a>
#### Response

**200 OK**

```text
{
  "content": [ContactPublic]
  "totalPages": number
  "totalElements": number
}
```

Notice that the pagination data covers more than these two properties, but these are the most useful.

<a id="markdown-get-apicontactscontactid" name="get-apicontactscontactid"></a>
### GET /api/contacts/{contactId}

Find a contact by his or her id.

<a id="markdown-request-parameters-1" name="request-parameters-1"></a>
#### Request parameters

|Name|Description|Type|Data type|
|---|---|---|---|
|`{contactId}`|Id of the contact to fetch|path|number|

<a id="markdown-response-1" name="response-1"></a>
#### Response

**200 OK**

```text
ContactPublic
```

<a id="markdown-post-apicontact" name="post-apicontact"></a>
### POST /api/contact/

Create a contact.

<a id="markdown-request-body" name="request-body"></a>
#### Request body

```text
ContactBase
```

<a id="markdown-response-2" name="response-2"></a>
#### Response

**201 Created**

```text
ContactPublic
```

<a id="markdown-put-apicontactscontactid" name="put-apicontactscontactid"></a>
### PUT /api/contacts/{contactId}

Update an existing contact.

<a id="markdown-request-parameters-2" name="request-parameters-2"></a>
#### Request parameters

|Name|Description|Type|Data type|
|---|---|---|---|
|`{contactId}`|Id of the contact to update|path|number|

<a id="markdown-request-body-1" name="request-body-1"></a>
#### Request body

```text
ContactBase
```

<a id="markdown-response-3" name="response-3"></a>
#### Response

**200 OK**

```text
ContactPublic
```

<a id="markdown-delete-apicontactcontactid" name="delete-apicontactcontactid"></a>
### DELETE /api/contact/{contactId}

<a id="markdown-request-parameters-3" name="request-parameters-3"></a>
#### Request parameters

|Name|Description|Type|Data type|
|---|---|---|---|
|`{contactId}`|Id of the contact to delete|path|number|

<a id="markdown-response-4" name="response-4"></a>
#### Response

**204 No Content**

<a id="markdown-entities" name="entities"></a>
## Entities

<a id="markdown-requests-to-server" name="requests-to-server"></a>
### Requests to server

<a id="markdown-contactbase" name="contactbase"></a>
#### ContactBase

Structure:

|Property|Observation|
|---|---|
|`firstName`|**Mandatory**, only alphabetic chars and white space.|
|`lastName`|**Mandatory**, only alphabetic chars and white space.|
|`email`|**Mandatory**, unique among contacts.|
|`company`|Optional, alphanumeric.|
|`phoneNumber`|Optional, only numbers, unique among contacts.|

```text
{
  "firstName": string,
  "lastName": string,
  "email": string,
  "company": string,
  "phoneNumber": string
}
```

Example:

```json
{
  "firstName": "Camila",
  "lastName": "Hughes",
  "email": "camila@example.com",
  "company": "Camila Co.",
  "phoneNumber": "9994535145"
}
```

<a id="markdown-responses-by-server" name="responses-by-server"></a>
### Responses by server

<a id="markdown-contactpublic" name="contactpublic"></a>
#### ContactPublic

Structure:

```text
{
  "contactId": number,
  "firstName": string,
  "lastName": string,
  "email": string,
  "company": string,
  "phoneNumber": string
}
```

Example:

```json
{
  "contactId": 5,
  "firstName": "Camila",
  "lastName": "Hughes",
  "email": "camila@example.com",
  "company": "Camila Co.",
  "phoneNumber": "9994535145"
}
```