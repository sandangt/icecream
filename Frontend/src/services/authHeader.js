export default () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user && user.token) {
        return {
    Authorization: `Bearer ${user.token}`,
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH',
    'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
        }
    }
    return {};
};