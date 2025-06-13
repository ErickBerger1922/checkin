import api from "./api";
import qs from "qs";

const clientId = "myclientid";
const clientSecret = "myclientsecret";

const login = async (loginRequest) => {
  const basicAuth = btoa(`${clientId}:${clientSecret}`);

  const response = await api.post(
    "/oauth2/token",
    qs.stringify(loginRequest),
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Authorization": `Basic ${basicAuth}`
      }
    }
  );

  return response.data;
};

export const authService = { login };
