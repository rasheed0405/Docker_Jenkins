# Docker_Jenkins

# 🚀 Jenkins CI/CD Pipeline with Docker & GitHub Webhooks

This project demonstrates how to set up an automated CI/CD pipeline using **Jenkins (Dockerized)** that triggers a **Maven build** every time you push code to a GitHub repository — via **Ngrok-powered webhooks**.

---

## 📂 Project Structure
Docker_Jenkins/
├── Jenkinsfile (optional)
├── Dockerfile
├── src/
├── pom.xml
└── README.md


## 📸 Example (GIFs)

| GitHub Push | Jenkins Auto-Trigger | Maven Build |
|-------------|----------------------|-------------|
| ![push](assets/push.gif) | ![trigger](assets/trigger.gif) | ![maven](assets/maven.gif) |

---

## 🛠️ Prerequisites

- [Docker](https://docs.docker.com/get-docker/)
- [GitHub Account](https://github.com/)
- [Ngrok](https://ngrok.com/)
- Maven project in GitHub (Java)


## 🐳 Step 1: Run Jenkins in Docker

```bash
docker run -d --name jenkinsrun \-p 8080:8080 -p 50000:50000 \jenkins/jenkins:lts

🔐 **Step 2: Unlock Jenkins**
Get initial password:
http://localhost:8080
docker exec jenkinsrun cat /var/jenkins_home/secrets/initialAdminPassword

⚙️ **Step 3: Create Jenkins Jo**b
New Item → Freestyle project

Source Code Management: Git
https://github.com/your-username/your-repo.git

Branch: main

Build Triggers:
✅ GitHub hook trigger for GITScm polling

Build Steps:
Add → Invoke top-level Maven targets
Goal: clean install

🌐 **Step 4: Expose Jenkins with Ngrok**
bash
Copy
Edit
docker run -d --name ngrok \-p 4040:4040 \ngrok/ngrok http host.docker.internal:8080 \--authtoken YOUR_NGROK_TOKEN
http://localhost:4040

🔗 **Step 5: Set Up GitHub Webhook**
Go to your GitHub repo → Settings → Webhooks → Add Webhook

**Field	Value**
Payload URL:	https://abc123.ngrok.io/github-webhook/(Different for everyone)
Content Type:	application/json
Secret:	(leave blank)
SSL Verify:	✅ Enable
Events:	✅ Just the push event

🔐 **Step 6: Jenkins Security Settings**
Global Security (in Jenkins)
Authorization: Logged-in users can do anything
✅ Allow anonymous read access

CSRF Protection:
✅ Enable Crumb Issuer
✅ Enable proxy compatibility


🧪 **Step 7: Push to GitHub**
git add .
git commit -m "Trigger Jenkins"
git push


📈 **Step 8: Verify Everything**
✅ GitHub:
Repo → Settings → Webhooks → Recent Deliveries
if its executed it will 200 OK Ressponse 

Should show ✅ 200 OK

✅ Jenkins:
Jenkins Dashboard → Click your job → Build History

Click latest build → Console Output to verify Maven build
