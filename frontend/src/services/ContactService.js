import axios from "axios";

// Frontend services are to connect the frontend with the service layer in the backend.

// The implmenetation is via factory functions which build Promises.
// What is a factory function?:
// https://medium.com/javascript-scene/javascript-factory-functions-with-es6-4d224591a8b1

export const ContactService = () => {
  const getContactsPaginated = ({ page, size }) => {
    return axios.get(`/api/contacts?page=${page}&size=${size}`);
  };

  const createContact = contact => {
    return axios.post("/api/contacts", contact);
  };

  const updateContact = (contact, contactId) => {
    axios.put(`/api/contacts/${contactId}`, contact);
  };

  const findContactById = contactId => {
    return axios.get(`/api/contacts/${contactId}`);
  };

  return {
    getContactsPaginated,
    createContact,
    updateContact,
    findContactById
  };
};
